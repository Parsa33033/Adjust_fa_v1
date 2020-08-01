import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './adjust-price.reducer';
import { IAdjustPrice } from 'app/shared/model/adjust-price.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAdjustPriceProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const AdjustPrice = (props: IAdjustPriceProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { adjustPriceList, match, loading } = props;
  return (
    <div>
      <h2 id="adjust-price-heading">
        <Translate contentKey="adjustApp.adjustPrice.home.title">Adjust Prices</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="adjustApp.adjustPrice.home.createLabel">Create new Adjust Price</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {adjustPriceList && adjustPriceList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.adjustPrice.name">Name</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.adjustPrice.token">Token</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.adjustPrice.price">Price</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {adjustPriceList.map((adjustPrice, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${adjustPrice.id}`} color="link" size="sm">
                      {adjustPrice.id}
                    </Button>
                  </td>
                  <td>{adjustPrice.name}</td>
                  <td>{adjustPrice.token}</td>
                  <td>{adjustPrice.price}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${adjustPrice.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${adjustPrice.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${adjustPrice.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="adjustApp.adjustPrice.home.notFound">No Adjust Prices found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ adjustPrice }: IRootState) => ({
  adjustPriceList: adjustPrice.entities,
  loading: adjustPrice.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AdjustPrice);
