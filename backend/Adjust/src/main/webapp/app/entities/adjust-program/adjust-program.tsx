import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './adjust-program.reducer';
import { IAdjustProgram } from 'app/shared/model/adjust-program.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAdjustProgramProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const AdjustProgram = (props: IAdjustProgramProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { adjustProgramList, match, loading } = props;
  return (
    <div>
      <h2 id="adjust-program-heading">
        <Translate contentKey="adjustApp.adjustProgram.home.title">Adjust Programs</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="adjustApp.adjustProgram.home.createLabel">Create new Adjust Program</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {adjustProgramList && adjustProgramList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.adjustProgram.createdAt">Created At</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.adjustProgram.expirationDate">Expiration Date</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.adjustProgram.designed">Designed</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.adjustProgram.done">Done</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.adjustProgram.paid">Paid</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.adjustProgram.bodyCompostion">Body Compostion</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.adjustProgram.fitnessProgram">Fitness Program</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.adjustProgram.nutritionProgram">Nutrition Program</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.adjustProgram.client">Client</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.adjustProgram.specialist">Specialist</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {adjustProgramList.map((adjustProgram, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${adjustProgram.id}`} color="link" size="sm">
                      {adjustProgram.id}
                    </Button>
                  </td>
                  <td>
                    {adjustProgram.createdAt ? (
                      <TextFormat type="date" value={adjustProgram.createdAt} format={APP_LOCAL_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>
                    {adjustProgram.expirationDate ? (
                      <TextFormat type="date" value={adjustProgram.expirationDate} format={APP_LOCAL_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{adjustProgram.designed ? 'true' : 'false'}</td>
                  <td>{adjustProgram.done ? 'true' : 'false'}</td>
                  <td>{adjustProgram.paid ? 'true' : 'false'}</td>
                  <td>
                    {adjustProgram.bodyCompostionId ? (
                      <Link to={`body-composition/${adjustProgram.bodyCompostionId}`}>{adjustProgram.bodyCompostionId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {adjustProgram.fitnessProgramId ? (
                      <Link to={`fitness-program/${adjustProgram.fitnessProgramId}`}>{adjustProgram.fitnessProgramId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {adjustProgram.nutritionProgramId ? (
                      <Link to={`nutrition-program/${adjustProgram.nutritionProgramId}`}>{adjustProgram.nutritionProgramId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {adjustProgram.clientId ? <Link to={`adjust-client/${adjustProgram.clientId}`}>{adjustProgram.clientId}</Link> : ''}
                  </td>
                  <td>
                    {adjustProgram.specialistId ? (
                      <Link to={`specialist/${adjustProgram.specialistId}`}>{adjustProgram.specialistId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${adjustProgram.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${adjustProgram.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${adjustProgram.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="adjustApp.adjustProgram.home.notFound">No Adjust Programs found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ adjustProgram }: IRootState) => ({
  adjustProgramList: adjustProgram.entities,
  loading: adjustProgram.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AdjustProgram);
