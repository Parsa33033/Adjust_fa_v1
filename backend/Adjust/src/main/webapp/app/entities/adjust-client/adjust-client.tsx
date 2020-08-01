import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { openFile, byteSize, Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './adjust-client.reducer';
import { IAdjustClient } from 'app/shared/model/adjust-client.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAdjustClientProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const AdjustClient = (props: IAdjustClientProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { adjustClientList, match, loading } = props;
  return (
    <div>
      <h2 id="adjust-client-heading">
        <Translate contentKey="adjustApp.adjustClient.home.title">Adjust Clients</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="adjustApp.adjustClient.home.createLabel">Create new Adjust Client</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {adjustClientList && adjustClientList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.adjustClient.username">Username</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.adjustClient.firstName">First Name</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.adjustClient.lastName">Last Name</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.adjustClient.birthDate">Birth Date</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.adjustClient.age">Age</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.adjustClient.gender">Gender</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.adjustClient.token">Token</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.adjustClient.score">Score</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.adjustClient.image">Image</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {adjustClientList.map((adjustClient, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${adjustClient.id}`} color="link" size="sm">
                      {adjustClient.id}
                    </Button>
                  </td>
                  <td>{adjustClient.username}</td>
                  <td>{adjustClient.firstName}</td>
                  <td>{adjustClient.lastName}</td>
                  <td>
                    {adjustClient.birthDate ? (
                      <TextFormat type="date" value={adjustClient.birthDate} format={APP_LOCAL_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{adjustClient.age}</td>
                  <td>
                    <Translate contentKey={`adjustApp.Gender.${adjustClient.gender}`} />
                  </td>
                  <td>{adjustClient.token}</td>
                  <td>{adjustClient.score}</td>
                  <td>
                    {adjustClient.image ? (
                      <div>
                        {adjustClient.imageContentType ? (
                          <a onClick={openFile(adjustClient.imageContentType, adjustClient.image)}>
                            <img src={`data:${adjustClient.imageContentType};base64,${adjustClient.image}`} style={{ maxHeight: '30px' }} />
                            &nbsp;
                          </a>
                        ) : null}
                        <span>
                          {adjustClient.imageContentType}, {byteSize(adjustClient.image)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${adjustClient.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${adjustClient.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${adjustClient.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="adjustApp.adjustClient.home.notFound">No Adjust Clients found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ adjustClient }: IRootState) => ({
  adjustClientList: adjustClient.entities,
  loading: adjustClient.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AdjustClient);
