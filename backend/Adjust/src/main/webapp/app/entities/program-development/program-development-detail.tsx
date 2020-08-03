import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './program-development.reducer';
import { IProgramDevelopment } from 'app/shared/model/program-development.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IProgramDevelopmentDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ProgramDevelopmentDetail = (props: IProgramDevelopmentDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { programDevelopmentEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="adjustApp.programDevelopment.detail.title">ProgramDevelopment</Translate> [
          <b>{programDevelopmentEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="date">
              <Translate contentKey="adjustApp.programDevelopment.date">Date</Translate>
            </span>
          </dt>
          <dd>
            {programDevelopmentEntity.date ? (
              <TextFormat value={programDevelopmentEntity.date} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="workoutScore">
              <Translate contentKey="adjustApp.programDevelopment.workoutScore">Workout Score</Translate>
            </span>
          </dt>
          <dd>{programDevelopmentEntity.workoutScore}</dd>
          <dt>
            <span id="fitnessScore">
              <Translate contentKey="adjustApp.programDevelopment.fitnessScore">Fitness Score</Translate>
            </span>
          </dt>
          <dd>{programDevelopmentEntity.fitnessScore}</dd>
          <dt>
            <Translate contentKey="adjustApp.programDevelopment.adjustProgram">Adjust Program</Translate>
          </dt>
          <dd>{programDevelopmentEntity.adjustProgramId ? programDevelopmentEntity.adjustProgramId : ''}</dd>
        </dl>
        <Button tag={Link} to="/program-development" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/program-development/${programDevelopmentEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ programDevelopment }: IRootState) => ({
  programDevelopmentEntity: programDevelopment.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ProgramDevelopmentDetail);
